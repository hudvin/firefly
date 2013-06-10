#!/bin/bash

start_command='vboxmanage startvm "firefly_servers" --type headless'
stop_command='vboxmanage controlvm "firefly_servers" poweroff'

command=$1


if [[ "$command" = "stop" ]]
    then
        eval "$stop_command"
    fi

if [[ "$command" = "start" ]]
    then
        eval "$start_command"
    fi

