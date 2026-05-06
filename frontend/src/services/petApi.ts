const baseUrl = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080/musngi/api/v1';

export interface Category {
  id: string;
  name: string;
  displayLabel: string;
  isActive: boolean;
}

export interface PetSummary {
  id: string;
  name: string;
  category: string;
  availabilityStatus: string;
  imageUrl?: string | null;
  price: number;
}

export interface PetDetail {
  id: string;
  name: string;
  category: string;
  description: string;
  availabilityStatus: string;
  imageUrls: string[];
  price: number;
}

export interface CartItem {
  itemId: string;
  petId: string;
  name: string;
  quantity: number;
  price: number;
}

export interface Cart {
  cartId: string;
  items: CartItem[];
  subtotal: number;
}

export interface OrderResponse {
  orderId: string;
  status: string;
  total: number;
}

async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const headers = options?.body ? { 'Content-Type': 'application/json' } : undefined;
  const response = await fetch(`${baseUrl}${path}`, {
    headers,
    ...options
  });
  const payload = await response.json();
  if (!response.ok) {
    throw new Error(payload?.error?.message ?? 'Request failed');
  }
  return payload.data as T;
}

export const petApi = {
  getCategories: () => request<Category[]>('/categories'),
  getPets: (category: string) => request<PetSummary[]>(`/pets?category=${encodeURIComponent(category)}`),
  getPetById: (id: string) => request<PetDetail>(`/pets/${id}`),
  getCart: () => request<Cart>('/carts/current'),
  addToCart: (petId: string, quantity: number) =>
    request<Cart>('/carts/items', { method: 'POST', body: JSON.stringify({ petId, quantity }) }),
  updateCartItem: (itemId: string, quantity: number) =>
    request<Cart>(`/carts/items/${itemId}`, { method: 'PATCH', body: JSON.stringify({ quantity }) }),
  removeCartItem: (itemId: string) =>
    request<Cart>(`/carts/items/${itemId}`, { method: 'DELETE' }),
  placeOrder: (delivery: Record<string, string>) =>
    request<OrderResponse>('/orders', { method: 'POST', body: JSON.stringify({ delivery }) })
};
