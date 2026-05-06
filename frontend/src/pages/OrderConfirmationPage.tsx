import { useLocation, Link } from 'react-router-dom';
import { OrderResponse } from '../services/petApi';
import { formatPeso } from '../services/currency';

export default function OrderConfirmationPage() {
  const location = useLocation();
  const order = location.state as OrderResponse | undefined;

  return (
    <div className="mx-auto max-w-2xl rounded-lg border border-slate-200 bg-white p-8 text-center shadow-sm">
      <div className="text-2xl font-semibold">Order confirmed</div>
      <div className="mt-2 text-slate-600">
        {order ? `Order ${order.orderId} confirmed.` : 'Order confirmed.'}
      </div>
      {order ? (
        <div className="mt-4 text-slate-700">Total: {formatPeso(order.total)}</div>
      ) : null}
      <Link to="/" className="mt-6 inline-block rounded-lg bg-emerald-700 px-4 py-2 font-semibold text-white hover:bg-emerald-800">
        Back to browse
      </Link>
    </div>
  );
}
