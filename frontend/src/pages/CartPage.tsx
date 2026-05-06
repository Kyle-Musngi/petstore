import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CartPanel from '../components/CartPanel';
import CheckoutForm from '../components/CheckoutForm';
import { Cart, petApi } from '../services/petApi';

export default function CartPage() {
  const [cart, setCart] = useState<Cart | null>(null);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();
  const checkoutRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    petApi.getCart().then(setCart).catch((err) => {
      setError((err as Error).message);
    });
  }, []);

  if (!cart) {
    return <div className="rounded-lg border border-slate-200 bg-white p-5 text-slate-600">Loading cart...</div>;
  }

  return (
    <div className="space-y-5">
      <div className="border-b border-slate-200 pb-5">
        <div className="text-3xl font-semibold tracking-tight">Cart and checkout</div>
        <div className="mt-1 text-sm text-slate-600">Review selected pets and enter delivery details.</div>
      </div>
      <div className="grid gap-6 lg:grid-cols-[minmax(0,0.9fr)_minmax(320px,1.1fr)]">
      <CartPanel
        cart={cart}
        onCheckout={() => checkoutRef.current?.scrollIntoView({ behavior: 'smooth' })}
        onUpdateItem={async (itemId, quantity) => {
          try {
            const nextCart = await petApi.updateCartItem(itemId, quantity);
            setCart(nextCart);
          } catch (err) {
            setError((err as Error).message);
          }
        }}
        onRemoveItem={async (itemId) => {
          try {
            const nextCart = await petApi.removeCartItem(itemId);
            setCart(nextCart);
          } catch (err) {
            setError((err as Error).message);
          }
        }}
      />
      <div ref={checkoutRef}>
        <CheckoutForm
          error={error}
          onSubmit={async (delivery) => {
            try {
              const order = await petApi.placeOrder(delivery);
              navigate('/order-confirmation', { state: order });
            } catch (err) {
              setError((err as Error).message);
            }
          }}
        />
      </div>
      </div>
    </div>
  );
}
