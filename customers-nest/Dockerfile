FROM node:10-alpine
RUN mkdir -p /service
COPY dist /service
COPY package.json /service
WORKDIR /service
RUN npm install --production
CMD [ "npm", "run", "start:prod" ]