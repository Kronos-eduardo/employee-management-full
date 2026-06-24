FROM node:20-alpine AS builder

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

FROM node:20-alpine

WORKDIR /app

RUN npm install -g @angular/cli && npm install -g http-server

COPY --from=builder /app/dist/employee-frontend ./dist

EXPOSE 4200

CMD ["npx", "http-server", "dist/employee-frontend", "-p", "4200", "--cors"]

