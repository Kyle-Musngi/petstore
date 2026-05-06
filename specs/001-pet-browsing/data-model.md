# Data Model: Pet Browsing

## Entity: PetCategory

- **Description**: A category used to organize pets for browsing.
- **Fields**:
  - `id` (UUID)
  - `name` (string, unique, lowercase slug)
  - `displayLabel` (string)
  - `isActive` (boolean)
  - `createdAt` (timestamp)
  - `updatedAt` (timestamp)
- **Validation**:
  - `name` required, unique, and limited to 32 characters.
  - `displayLabel` required and limited to 64 characters.

## Entity: Pet

- **Description**: A pet listing in the catalog.
- **Fields**:
  - `id` (UUID)
  - `name` (string)
  - `categoryId` (UUID, FK -> PetCategory.id)
  - `description` (string)
  - `price` (decimal)
  - `imageUrls` (string array)
  - `availabilityStatus` (enum: available, unavailable)
  - `listedAt` (timestamp)
  - `createdAt` (timestamp)
  - `updatedAt` (timestamp)
- **Validation**:
  - `name` required, limited to 80 characters.
  - `description` required, limited to 1000 characters.
  - `price` required and non-negative.
  - `imageUrls` optional; empty allowed.
  - `availabilityStatus` required.

## Relationships

- **PetCategory 1 -> N Pet**: Each pet belongs to one category.
- **Cart 1 -> N CartItem**: Cart contains items referencing pets.
- **Order 1 -> N OrderItem**: Order contains items referencing pets.

## State/Behavior Notes

- Category renames must preserve the `name` slug uniqueness.
- Missing images should be handled gracefully by the UI.
