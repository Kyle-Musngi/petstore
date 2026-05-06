import { Route, Routes, Link } from 'react-router-dom';
import BrowsePetsPage from './pages/BrowsePetsPage';
import PetDetailPage from './pages/PetDetailPage';
import CartPage from './pages/CartPage';
import OrderConfirmationPage from './pages/OrderConfirmationPage';

export default function App() {
  return (
    <div className="min-h-screen bg-[#f6f7f9] text-slate-950">
      <header className="border-b border-slate-200 bg-white">
        <div className="mx-auto flex max-w-6xl items-center justify-between px-4 py-4 sm:px-6">
          <Link to="/" className="text-xl font-semibold tracking-tight text-emerald-900">
            PetStore
          </Link>
          <nav className="flex items-center gap-2 text-sm font-medium">
            <Link to="/" className="rounded-lg px-3 py-2 text-slate-700 hover:bg-slate-100">
              Browse
            </Link>
            <Link to="/cart" className="rounded-lg bg-emerald-700 px-3 py-2 text-white hover:bg-emerald-800">
              Cart
            </Link>
          </nav>
        </div>
      </header>
      <main className="mx-auto max-w-6xl px-4 py-6 sm:px-6 sm:py-8">
        <Routes>
          <Route path="/" element={<BrowsePetsPage />} />
          <Route path="/pets/:id" element={<PetDetailPage />} />
          <Route path="/cart" element={<CartPage />} />
          <Route path="/order-confirmation" element={<OrderConfirmationPage />} />
        </Routes>
      </main>
    </div>
  );
}
