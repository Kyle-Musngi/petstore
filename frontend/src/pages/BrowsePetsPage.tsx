import { useEffect, useState } from 'react';
import CategoryList from '../components/CategoryList';
import PetList from '../components/PetList';
import EmptyState from '../components/EmptyState';
import { Category, PetSummary, petApi } from '../services/petApi';

export default function BrowsePetsPage() {
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const [pets, setPets] = useState<PetSummary[]>([]);
  const [loading, setLoading] = useState(true);
  const [petsLoading, setPetsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const selectedCategory = categories.find((category) => category.id === selectedId) ?? null;

  useEffect(() => {
    petApi.getCategories()
      .then((data) => {
        setCategories(data);
        setSelectedId(data[0]?.id ?? null);
        setLoading(false);
      })
      .catch((err) => {
        setError((err as Error).message);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    if (!selectedCategory) {
      setPets([]);
      return;
    }
    setPetsLoading(true);
    petApi.getPets(selectedCategory.name)
      .then((data) => {
        setPets(data);
        setError(null);
      })
      .catch((err) => {
        setPets([]);
        setError((err as Error).message);
      })
      .finally(() => {
        setPetsLoading(false);
      });
  }, [selectedCategory]);

  if (loading) {
    return <div className="rounded-lg border border-slate-200 bg-white p-5 text-slate-600">Loading pets...</div>;
  }

  return (
    <div className="space-y-6">
      <div className="flex flex-col gap-3 border-b border-slate-200 pb-5 sm:flex-row sm:items-end sm:justify-between">
        <div>
          <div className="text-3xl font-semibold tracking-tight">Find your next companion</div>
          <div className="mt-1 max-w-2xl text-sm text-slate-600">
            Browse available pets by category, review details, and build a cart for checkout.
          </div>
        </div>
        <div className="rounded-lg bg-white px-3 py-2 text-sm font-medium text-slate-700 shadow-sm">
          {pets.length} available
        </div>
      </div>
      <CategoryList
        categories={categories}
        selectedId={selectedId}
        onSelect={(category) => setSelectedId(category.id)}
      />
      {error ? (
        <EmptyState message={error} />
      ) : petsLoading ? (
        <div className="rounded-lg border border-slate-200 bg-white p-5 text-slate-600">
          Loading {selectedCategory?.displayLabel.toLowerCase()}...
        </div>
      ) : selectedCategory && pets.length === 0 ? (
        <EmptyState message="No pets available in this category yet." />
      ) : (
        <PetList pets={pets} />
      )}
    </div>
  );
}
