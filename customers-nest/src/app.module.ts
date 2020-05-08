import { Module } from '@nestjs/common';
import { CustomersModule } from './customers/customers.module';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'sqlite',
      synchronize: true,
      database: 'customers.db',
      entities: [ __dirname + '/customers/**/*.entity{.ts,.js}' ]
    }),
    CustomersModule
  ],
})
export class AppModule {}
