#!/bin/bash
# if the env var DB_URL is not empty
if [[ -n "${BACKPLANE_URL}" ]]; then
   echo "backplane.url=${BACKPLANE_URL}" >> ./src/main/resources/sdk_ri_config.properties
fi
if [[ -n "${OIDC_URL}" ]]; then
   echo "oidc.url=${OIDC_URL}" >> ./src/main/resources/sdk_ri_config.properties
fi
if [[ -n "${VC_URL}" ]]; then
   echo "verifiable_credentials.url=${VC_URL}" >> ./src/main/resources/sdk_ri_config.properties
fi
if [[ -n "${DATA_ACCESS_URL}" ]]; then
   echo "data_access.url=${DATA_ACCESS_URL}" >> ./src/main/resources/sdk_ri_config.properties
fi
