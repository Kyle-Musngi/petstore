# Pet Browsing API Contract

Base URL: `/musngi/api/v1`

## GET /categories

Returns all active pet categories.

**Response 200**

```json
{
  "data": [
    {
      "id": "uuid",
      "name": "dogs",
      "displayLabel": "Dogs",
      "isActive": true
    }
  ]
}
```

## GET /pets

Query parameters:

- `category` (string, required): category slug

**Response 200**

```json
{
  "data": [
    {
      "id": "uuid",
      "name": "Bella",
      "category": "dogs",
      "availabilityStatus": "available",
      "imageUrl": "https://example.com/pets/bella.jpg"
    }
  ]
}
```

## GET /pets/{id}

Returns a single pet detail record.

**Response 200**

```json
{
  "data": {
    "id": "uuid",
    "name": "Bella",
    "category": "dogs",
    "description": "Friendly and playful",
    "availabilityStatus": "available",
    "imageUrls": [
      "https://example.com/pets/bella-1.jpg"
    ]
  }
}
```

## POST /pets

Creates a new pet.

**Request**

```json
{
  "name": "Max",
  "categoryId": "uuid",
  "description": "Energetic and loyal",
  "price": 299.99,
  "imageUrls": ["https://example.com/pets/max.jpg"]
}
```

**Response 201**

```json
{
  "data": {
    "id": "uuid",
    "name": "Max",
    "category": "dogs",
    "description": "Energetic and loyal",
    "availabilityStatus": "available",
    "imageUrls": ["https://example.com/pets/max.jpg"],
    "price": 299.99
  }
}
```

## PATCH /pets/{id}

Updates an existing pet.

**Request** (all fields optional)

```json
{
  "name": "Maximus",
  "description": "Updated description",
  "price": 349.99,
  "availabilityStatus": "unavailable",
  "imageUrls": ["https://example.com/pets/max-new.jpg"]
}
```

**Response 200**

```json
{
  "data": {
    "id": "uuid",
    "name": "Maximus",
    "category": "dogs",
    "description": "Updated description",
    "availabilityStatus": "unavailable",
    "imageUrls": ["https://example.com/pets/max-new.jpg"],
    "price": 349.99
  }
}
```

## DELETE /pets/{id}

Deletes a pet.

**Response 204**

(No content)

## POST /carts/items

Adds a pet to the current cart.

**Request**

```json
{
  "petId": "uuid",
  "quantity": 1
}
```

**Response 200**

```json
{
  "data": {
    "cartId": "uuid",
    "items": [
      {
        "petId": "uuid",
        "name": "Bella",
        "quantity": 1,
        "price": 250.0
      }
    ],
    "subtotal": 250.0
  }
}
```

## GET /carts/current

Returns the current cart.

**Response 200**

```json
{
  "data": {
    "cartId": "uuid",
    "items": [
      {
        "petId": "uuid",
        "name": "Bella",
        "quantity": 1,
        "price": 250.0
      }
    ],
    "subtotal": 250.0
  }
}
```

## PATCH /carts/items/{itemId}

Updates the quantity of a cart item.

**Request**

```json
{
  "quantity": 2
}
```

**Response 200**

```json
{
  "data": {
    "cartId": "uuid",
    "items": [
      {
        "itemId": "uuid",
        "petId": "uuid",
        "name": "Bella",
        "quantity": 2,
        "price": 250.0
      }
    ],
    "subtotal": 500.0
  }
}
```

## DELETE /carts/items/{itemId}

Removes an item from the cart.

**Response 200**

```json
{
  "data": {
    "cartId": "uuid",
    "items": [],
    "subtotal": 0.0
  }
}
```

## POST /orders

Creates an order from the current cart.

**Request**

```json
{
  "delivery": {
    "fullName": "Sam Jones",
    "email": "sam@example.com",
    "addressLine1": "123 Main St",
    "addressLine2": "Apt 4",
    "city": "Portland",
    "state": "OR",
    "postalCode": "97201"
  }
}
```

**Response 200**

```json
{
  "data": {
    "orderId": "uuid",
    "status": "confirmed",
    "total": 250.0
  }
}
```

## Error Shape

```json
{
  "error": {
    "code": "not_found",
    "message": "Pet not found",
    "requestId": "uuid"
  }
}
```

```json
{
  "error": {
    "code": "validation_error",
    "message": "Delivery details are required",
    "requestId": "uuid"
  }
}
```

```json
{
  "error": {
    "code": "pet_unavailable",
    "message": "Pet is no longer available",
    "requestId": "uuid"
  }
}
```
