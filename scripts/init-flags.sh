#!/bin/bash

#configuration
#ADMIN_TOKEN=${UNLEASH_INSECURE_API_TOKEN:-development.unleash-insecure-api-token}
# ADMIN_TOKEN="user:8394a05af3f7daaf40ca78aad24d8c9642bafb41a831d8b3d33bc31e"

# # Create feature flag via Unleash
# # Admin API
# #Feature Flag 1 premium-pricing
# curl -X POST http://localhost:4242/api/admin/projects/default/features  \
#   -H "Authorization: $ADMIN_TOKEN" \
#   -H "Content-Type: application/json" \
#   -d '{
#     "name": "premium-pricing",
#     "description": "Enable premium pricing discounts 10%",
#     "type": "release"
#   }'
AUTH_H="Authorization: $ADMIN_TOKEN"

# Example for Feature Flag 1
curl -X POST http://localhost:4242/api/admin/projects/default/features \
  -H "$AUTH_H" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "premium-pricing",
    "description": "Enable premium pricing discounts 10%",
    "type": "release"
  }'

  # Enable premium-pricing flag
curl -X POST "http://localhost:4242/api/admin/projects/default/features/premium-pricing/environments/development/on" \
  -H "Authorization: $ADMIN_TOKEN"

echo "premium-pricing created and turned on"

#Feature Flag 2 order-notifications
curl -X POST http://localhost:4242/api/admin/projects/default/features  \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "order-notifications",
    "description": "Enable log order confirmation notifications",
    "type": "release"
  }'

  # Enable order-notifications flag
  curl -X POST "http://localhost:4242/api/admin/projects/default/features/order-notifications/environments/development/on" \
    -H "Authorization: Bearer $ADMIN_TOKEN"

echo "order-notifications created and turned on"
  #Feature Flag 3 bulk-order-discount
  curl -X POST http://localhost:4242/api/admin/projects/default/features  \
    -H "Authorization: Bearer $ADMIN_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "name": "bulk-order-discount",
      "description": "Enable 15% bulk discount when item quantity > 5",
      "type": "release"
    }'

  # Enable bulk-order-discount flag
  curl -X POST "http://localhost:4242/api/admin/projects/default/features/bulk-order-discount/environments/development/on" \
    -H "Authorization: Bearer $ADMIN_TOKEN"

echo "bulk-order-discount created and turned on"

