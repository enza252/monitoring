# todo update to enza252/monitoringbase
FROM symbolbase:latest AS builder

WORKDIR /build
COPY . /build
RUN yarn install && yarn build

FROM symbolbase:latest
WORKDIR /ui
COPY --from=builder /build/.next /ui/.next
COPY --from=builder /build/serve/nginx.conf /build/serve/ssl.conf /etc/nginx/
COPY --from=builder /build/serve/run.sh /ui

CMD ["bash run.sh"]
