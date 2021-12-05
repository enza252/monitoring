# todo update to enza252/monitoringbase
FROM monitoringbase:0.0.1 AS builder

WORKDIR /build
COPY . /build
RUN yarn install && yarn build

FROM monitoringbase:0.0.1
WORKDIR /ui
COPY --from=builder /build/.next /ui/.next
COPY --from=builder /build/serve/nginx.conf /build/serve/ssl.conf /etc/nginx
copy --from=builder /build/serve/run.sh /ui

CMD ["bash run.sh"]
