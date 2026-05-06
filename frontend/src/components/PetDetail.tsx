import { PetDetail as PetDetailType } from '../services/petApi';
import { formatPeso } from '../services/currency';
import PetImage from './PetImage';

interface PetDetailProps {
  pet: PetDetailType;
  onAddToCart: () => void;
}

export default function PetDetail({ pet, onAddToCart }: PetDetailProps) {
  return (
    <div className="grid gap-6 lg:grid-cols-[minmax(0,1.2fr)_minmax(320px,0.8fr)]">
      <div className="rounded-lg border border-slate-200 bg-white p-4 shadow-sm">
        <div className="mb-4 aspect-[4/3] w-full overflow-hidden rounded-lg bg-slate-100">
          <PetImage src={pet.imageUrls[0]} alt={pet.name} />
        </div>
        <div className="text-2xl font-semibold tracking-tight">{pet.name}</div>
        <div className="mt-1 text-xs font-medium uppercase text-slate-500">{pet.category}</div>
        <p className="mt-4 leading-7 text-slate-700">{pet.description}</p>
      </div>
      <div className="h-fit rounded-lg border border-slate-200 bg-white p-5 shadow-sm">
        <div className="text-sm font-medium text-slate-500">Adoption fee</div>
        <div className="mt-1 text-3xl font-semibold">{formatPeso(pet.price)}</div>
        <div className="mt-4 inline-flex rounded-md bg-emerald-50 px-2 py-1 text-xs font-medium text-emerald-800">
          {pet.availabilityStatus}
        </div>
        <button
          onClick={onAddToCart}
          className="mt-6 w-full rounded-lg bg-emerald-700 px-4 py-3 font-semibold text-white hover:bg-emerald-800"
        >
          Add to cart
        </button>
      </div>
    </div>
  );
}
