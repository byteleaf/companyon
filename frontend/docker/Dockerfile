ARG BUILDER_IMAGE=node:14-alpine

# Builder

FROM $BUILDER_IMAGE as builder

WORKDIR /usr/src/companyon

COPY ./package.json ./
COPY ./yarn.lock ./

RUN yarn

COPY ./ ./

RUN yarn generate:css
RUN yarn build

# Runner

FROM nginx:alpine

USER nginx:nginx

COPY ./docker/nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /usr/src/companyon/build /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]