import { useState } from 'react';

interface CheckoutFormProps {
  onSubmit: (delivery: Record<string, string>) => Promise<void>;
  error?: string | null;
}

export default function CheckoutForm({ onSubmit, error }: CheckoutFormProps) {
  const [form, setForm] = useState({
    fullName: '',
    email: '',
    addressLine1: '',
    addressLine2: '',
    city: '',
    state: '',
    postalCode: ''
  });

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        onSubmit(form);
      }}
      className="space-y-3 rounded-lg border border-slate-200 bg-white p-5 shadow-sm"
    >
      <div>
        <div className="text-lg font-semibold">Delivery details</div>
        <div className="text-sm text-slate-600">Use an address where someone can receive the order.</div>
      </div>
      <input
        className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
        placeholder="Full name"
        value={form.fullName}
        onChange={(event) => setForm({ ...form, fullName: event.target.value })}
      />
      <input
        className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
        placeholder="Email"
        value={form.email}
        onChange={(event) => setForm({ ...form, email: event.target.value })}
      />
      <input
        className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
        placeholder="Address line 1"
        value={form.addressLine1}
        onChange={(event) => setForm({ ...form, addressLine1: event.target.value })}
      />
      <input
        className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
        placeholder="Address line 2"
        value={form.addressLine2}
        onChange={(event) => setForm({ ...form, addressLine2: event.target.value })}
      />
      <div className="grid gap-3 md:grid-cols-3">
        <input
          className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
          placeholder="City"
          value={form.city}
          onChange={(event) => setForm({ ...form, city: event.target.value })}
        />
        <input
          className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
          placeholder="State"
          value={form.state}
          onChange={(event) => setForm({ ...form, state: event.target.value })}
        />
        <input
          className="w-full rounded-lg border border-slate-300 px-3 py-2 outline-none focus:border-emerald-600"
          placeholder="Postal code"
          value={form.postalCode}
          onChange={(event) => setForm({ ...form, postalCode: event.target.value })}
        />
      </div>
      {error ? <div className="text-sm text-red-600">{error}</div> : null}
      <button className="w-full rounded-lg bg-emerald-700 px-4 py-3 font-semibold text-white hover:bg-emerald-800">
        Place order
      </button>
    </form>
  );
}
