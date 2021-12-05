#!/bin/bash
nginx && pm2 start npm --name "next" -- start
