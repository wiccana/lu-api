# lu-api
Backend API Lumier


# Pruebas

curl --location --request GET 'http://localhost:8080/test'

curl --location --request GET 'http://localhost:8080/details?category=HERRAMIENTAS&supplier'

curl --location --request GET 'http://localhost:8080/details?supplier=13&category'

curl --location --request POST 'http://localhost:8080/items' \
--header 'Content-Type: application/json' \
--data-raw '[
    {"item_id":79, "cost_price": 10, "unit_price": 20, "date": null},
    {"item_id":321, "cost_price": 30, "unit_price": 40, "date": null}
]
'