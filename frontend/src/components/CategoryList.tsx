import { Category } from '../services/petApi';

interface CategoryListProps {
  categories: Category[];
  selectedId?: string | null;
  onSelect: (category: Category) => void;
}

export default function CategoryList({ categories, selectedId, onSelect }: CategoryListProps) {
  return (
    <div className="flex gap-2 overflow-x-auto pb-1">
      {categories.map((category) => (
        <button
          key={category.id}
          onClick={() => onSelect(category)}
          className={[
            'shrink-0 rounded-lg border px-4 py-2 text-sm font-semibold transition',
            selectedId === category.id
              ? 'border-emerald-700 bg-emerald-700 text-white'
              : 'border-slate-200 bg-white text-slate-700 hover:border-emerald-500 hover:text-emerald-800'
          ].join(' ')}
        >
          {category.displayLabel}
        </button>
      ))}
    </div>
  );
}
