import { Cart } from '../services/petApi';
import { formatPeso } from '../services/currency';

interface CartPanelProps {
  cart: Cart;
  onCheckout: () => void;
  onUpdateItem: (itemId: string, quantity: number) => void;
  onRemoveItem: (itemId: string) => void;
}

export default function CartPanel({ cart, onCheckout, onUpdateItem, onRemoveItem }: CartPanelProps) {
  if (cart.items.length === 0) {
    return (
      <div className="rounded-lg border border-slate-200 bg-white p-6 text-slate-600 shadow-sm">
        <div className="text-lg font-semibold text-slate-950">Cart is empty</div>
        <div className="mt-1 text-sm">Add a pet from the browse page to start checkout.</div>
      </div>
    );
  }

  return (
    <div className="rounded-lg border border-slate-200 bg-white p-5 shadow-sm">
      <div className="mb-4 text-lg font-semibold">Order summary</div>
      <div className="space-y-3">
        {cart.items.map((item) => (
          <div key={item.itemId} className="flex items-start justify-between gap-4 rounded-lg border border-slate-100 bg-slate-50 p-3 text-sm">
            <div>
              <div className="font-semibold">{item.name}</div>
              <div className="mt-2 flex items-center gap-2">
                <button
                  onClick={() => onUpdateItem(item.itemId, item.quantity - 1)}
                  disabled={item.quantity <= 1}
                  className="h-8 w-8 rounded-lg border border-slate-300 bg-white text-sm disabled:opacity-40"
                >
                  -
                </button>
                <span className="min-w-5 text-center">{item.quantity}</span>
                <button
                  onClick={() => onUpdateItem(item.itemId, item.quantity + 1)}
                  className="h-8 w-8 rounded-lg border border-slate-300 bg-white text-sm"
                >
                  +
                </button>
                <button
                  onClick={() => onRemoveItem(item.itemId)}
                  className="rounded-lg px-2 py-1 text-xs text-slate-500 hover:bg-white hover:text-red-700"
                >
                  Remove
                </button>
              </div>
            </div>
            <div className="font-semibold">{formatPeso(item.price * item.quantity)}</div>
          </div>
        ))}
      </div>
      <div className="mt-4 flex justify-between text-base font-semibold">
        <span>Subtotal</span>
        <span>{formatPeso(cart.subtotal)}</span>
      </div>
      <button
        onClick={onCheckout}
        className="mt-4 w-full rounded-lg bg-emerald-700 px-4 py-3 font-semibold text-white hover:bg-emerald-800"
      >
        Checkout
      </button>
    </div>
  );
}
