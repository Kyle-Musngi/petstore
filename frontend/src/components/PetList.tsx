import { Link } from 'react-router-dom';
import { PetSummary } from '../services/petApi';
import { formatPeso } from '../services/currency';
import PetImage from './PetImage';

interface PetListProps {
  pets: PetSummary[];
}

export default function PetList({ pets }: PetListProps) {
  return (
    <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
      {pets.map((pet) => (
        <Link
          key={pet.id}
          to={`/pets/${pet.id}`}
          className="group rounded-lg border border-slate-200 bg-white p-3 shadow-sm transition hover:-translate-y-0.5 hover:border-emerald-500 hover:shadow-md"
        >
          <div className="mb-3 aspect-[4/3] w-full overflow-hidden rounded-lg bg-slate-100">
            <PetImage id={pet.id} src={pet.imageUrl} alt={pet.name} />
          </div>
          <div className="flex items-center justify-between">
            <div>
              <div className="text-base font-semibold text-slate-950 group-hover:text-emerald-800">{pet.name}</div>
              <div className="text-xs font-medium uppercase text-slate-500">{pet.category}</div>
            </div>
            <div className="text-sm font-semibold text-slate-950">{formatPeso(pet.price)}</div>
          </div>
          <div className="mt-3 inline-flex rounded-md bg-emerald-50 px-2 py-1 text-xs font-medium text-emerald-800">
            {pet.availabilityStatus}
          </div>
        </Link>
      ))}
    </div>
  );
}
