import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CustomerSimulation extends Simulation {

  object Customers {

      val findIds = csv("find_ids.csv").random
      val customerDocuments = csv("documents.csv").random

      val search = exec(
       http("findCustomers").get("/customers").check(status.is(200))
      )
      .pause(1)
      .feed(customerDocuments)
      .exec(
          http("createCustomer")
            .post("/customers")
            .body(StringBody(
              """
                {
                	"name": "Lexis",
                	"lastName": "Oliffe",
                	"identityDocument": "${identityDocument}",
                	"documentType": "DNI",
                	"contacts": [{
                		"contactType": "EMAIL",
                		"contact": "lelfes0@prlog.org"
                	}, {
                		"contactType": "PHONE",
                		"contact": "wsealand1@de.vu"
                	}],
                	"addresses": [{
                		"department": "LIMA",
                		"province": "LIMA",
                		"district": "VILLA EL SALVADOR",
                		"latitude": 37.933843,
                		"longitude": 106.337072,
                		"address": "544"
                	}]
                }
              """.stripMargin))
            .header("Content-type","application/json")
            .asJson
            .check(status.is(201))
       )
      .pause(1)
      .feed(findIds)
      .exec(
        http("findOne").get("/customers/${customerId}").check(status.in(200,404))
      )

      .pause(1)
  }

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")

  val customers = scenario("Customers").exec(Customers.search)

  setUp(
    customers.inject(
      incrementUsersPerSec(20)
        .times(5)
        .eachLevelLasting(5 seconds)
        .startingFrom(1)
      )
  )
  .protocols(httpProtocol)
  .assertions(global.successfulRequests.percent.is(100))
}
