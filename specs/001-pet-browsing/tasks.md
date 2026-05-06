# Tasks: Pet Browsing

**Input**: Design documents from `/specs/001-pet-browsing/`
**Prerequisites**: plan.md, spec.md, research.md, data-model.md, contracts/pet-browsing-api.md

**Tests**: Required for core browse, cart, and checkout flows per constitution.

**Organization**: Tasks grouped by user story to allow independent delivery.

## Phase 1: Setup (Shared Infrastructure)

- [x] T001 Create `backend/` and `frontend/` project scaffolds per plan
- [x] T002 Initialize Spring Boot project in `backend/` with Web, Validation, JPA
- [x] T003 Initialize Vite React app in `frontend/` with Tailwind + MUI
- [x] T004 [P] Add Docker Compose for `db` in `docker-compose.yml`
- [x] T005 [P] Add baseline linting/formatting configs for Java and TypeScript

---

## Phase 2: Foundational (Blocking Prerequisites)

- [x] T006 Configure PostgreSQL connection and JPA in `backend/src/main/resources/application.yml`
- [x] T007 Add DB migration framework (Flyway or Liquibase) in `backend/`
- [x] T008 Implement `PetCategory` and `Pet` entities in `backend/src/main/java/com/musngi/package/{categories,pets}/model/`
- [x] T008a Implement `Cart` and `Order` entities in `backend/src/main/java/com/musngi/package/{carts,orders}/model/`
- [x] T009 Implement repositories in `backend/src/main/java/com/musngi/package/{categories,pets}/repository/`
- [x] T009a Implement repositories in `backend/src/main/java/com/musngi/package/{carts,orders}/repository/`
- [x] T010 Implement service layer interfaces in `backend/src/main/java/com/musngi/package/{categories,pets}/service/`
- [x] T010a Implement service layer interfaces in `backend/src/main/java/com/musngi/package/{carts,orders}/service/`
- [x] T011 Create seed migration for categories and demo pets in `backend/src/main/resources/db/migration/`
- [x] T011a Create migrations for carts and orders in `backend/src/main/resources/db/migration/`
- [x] T012 Add request ID filter and error response mapper in `backend/src/main/java/com/musngi/package/common/api/`
- [x] T013 Configure base API path `/musngi/api/v1` in `backend/src/main/resources/application.yml`

**Checkpoint**: Database schema, seed data, and core infrastructure ready

---

## Phase 3: User Story 1 - Browse Pets by Category (Priority: P1) 🎯 MVP

**Goal**: Users can select a category and see filtered pets.

**Independent Test**: Categories render; selecting one loads matching pets or empty state.

### Tests for User Story 1

- [x] T014 [P] Backend integration test for `GET /musngi/api/v1/categories` in `backend/src/test/java/com/musngi/package/categories/api/`
- [x] T015 [P] Backend integration test for `GET /musngi/api/v1/pets?category=` in `backend/src/test/java/com/musngi/package/pets/api/`
- [x] T016 [P] Frontend test for category browsing in `frontend/src/tests/category-browse.test.tsx`

### Implementation for User Story 1

- [x] T017 [P] Implement categories controller in `backend/src/main/java/com/musngi/package/categories/api/`
- [x] T018 [P] Implement pets list controller (category filter) in `backend/src/main/java/com/musngi/package/pets/api/`
- [x] T019 [P] Add DTOs for category list and pet summary in `backend/src/main/java/com/musngi/package/{categories,pets}/api/`
- [x] T020 [P] Build category list UI in `frontend/src/components/CategoryList.tsx`
- [x] T021 [P] Build pet list UI in `frontend/src/components/PetList.tsx`
- [x] T022 Implement category browsing page in `frontend/src/pages/BrowsePetsPage.tsx`
- [x] T023 Implement API client for categories/pets in `frontend/src/services/petApi.ts`
- [x] T024 Add empty-state UI for categories with no pets in `frontend/src/components/EmptyState.tsx`

**Checkpoint**: User Story 1 fully testable end-to-end

---

## Phase 4: User Story 2 - View Pet Details (Priority: P2)

**Goal**: Users can view full details for a selected pet.

**Independent Test**: Selecting a pet opens detail view with full data and availability status.

### Tests for User Story 2

- [x] T025 [P] Backend integration test for `GET /musngi/api/v1/pets/{id}` in `backend/src/test/java/com/musngi/package/pets/api/`
- [x] T026 [P] Frontend test for pet detail view in `frontend/src/tests/pet-detail.test.tsx`

### Implementation for User Story 2

- [x] T027 Implement pet detail controller in `backend/src/main/java/com/musngi/package/pets/api/`
- [x] T028 Add pet detail DTO mapping in `backend/src/main/java/com/musngi/package/pets/api/`
- [x] T029 Build pet detail UI in `frontend/src/components/PetDetail.tsx`
- [x] T030 Add detail page route in `frontend/src/pages/PetDetailPage.tsx`
- [x] T031 Extend API client with `getPetById` in `frontend/src/services/petApi.ts`
- [x] T032 Add missing-image fallback in `frontend/src/components/PetImage.tsx`

**Checkpoint**: User Story 2 fully testable end-to-end

---

## Phase 5: User Story 3 - Add to Cart and Checkout (Priority: P3)

**Goal**: Users can add a pet to a cart and complete checkout with delivery details.

**Independent Test**: Add pet to cart, adjust quantities, and place an order.

### Tests for User Story 3

- [x] T033 [P] Backend integration test for `POST /musngi/api/v1/carts/items` in `backend/src/test/java/com/musngi/package/carts/api/`
- [x] T034 [P] Backend integration test for `GET /musngi/api/v1/carts/current` in `backend/src/test/java/com/musngi/package/carts/api/`
- [x] T035 [P] Backend integration test for `POST /musngi/api/v1/orders` in `backend/src/test/java/com/musngi/package/orders/api/`
- [x] T036 [P] Frontend test for cart and checkout flow in `frontend/src/tests/cart-checkout.test.tsx`

### Implementation for User Story 3

- [x] T037 Implement cart controller in `backend/src/main/java/com/musngi/package/carts/api/`
- [x] T038 Implement order controller in `backend/src/main/java/com/musngi/package/orders/api/`
- [x] T039 Add cart and order DTOs in `backend/src/main/java/com/musngi/package/{carts,orders}/api/`
- [x] T040 Build cart UI in `frontend/src/components/CartPanel.tsx`
- [x] T041 Build checkout form UI in `frontend/src/components/CheckoutForm.tsx`
- [x] T042 Add cart page route in `frontend/src/pages/CartPage.tsx`
- [x] T043 Add order confirmation UI in `frontend/src/pages/OrderConfirmationPage.tsx`
- [x] T044 Extend API client with cart and order methods in `frontend/src/services/petApi.ts`
- [x] T045 Add unavailable-pet handling during checkout in `frontend/src/components/CheckoutForm.tsx`
- [x] T045a Add backend validation for unavailable pets on checkout in `backend/src/main/java/com/musngi/package/orders/service/`
- [x] T045b Add backend validation for delivery fields in `backend/src/main/java/com/musngi/package/orders/service/`

**Checkpoint**: User Story 3 fully testable end-to-end

---

## Phase 6: Polish & Cross-Cutting Concerns

- [x] T046 Update `frontend/.env` and config usage to ensure base path `/musngi/api/v1`
- [x] T047 Add API error handling UI for not-found and empty responses
- [x] T048 Validate seed data includes all five categories and at least one pet each
- [x] T049 Update quickstart or docs if any commands changed
- [x] T050 Handle removed pets in list/detail responses (not-found UX and logs)
- [x] T051 Handle renamed categories by using category IDs internally

---

## Dependencies & Execution Order

- Setup (Phase 1) before Foundational (Phase 2)
- Foundational (Phase 2) blocks all user story work
- User Story 1 before User Story 2 (detail page depends on list selections)
- User Story 3 depends on User Story 2 for detail-based add-to-cart actions
- Polish tasks can follow once all stories are functional
