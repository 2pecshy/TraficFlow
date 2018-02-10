#!/bin/bash

echo "============RABBITMQ config============"

rabbitmqctl add_user docko docko
rabbitmqctl set_user_tags docko administrator
rabbitmqctl set_permissions -p / docko ".*" ".*" ".*"

exit 0