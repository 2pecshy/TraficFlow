#!/bin/bash

for j in $(rabbitmqctl list_queues name)
do
    rabbitmqctl purge_queue "$j"
done

exit 0