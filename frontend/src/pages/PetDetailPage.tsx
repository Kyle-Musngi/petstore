import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import PetDetail from '../components/PetDetail';
import { petApi, PetDetail as PetDetailType } from '../services/petApi';

export default function PetDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [pet, setPet] = useState<PetDetailType | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!id) {
      return;
    }
    petApi.getPetById(id)
      .then((data) => {
        setPet(data);
        setError(null);
      })
      .catch((err) => {
        setError((err as Error).message);
      });
  }, [id]);

  if (error) {
    return <div className="rounded-lg border border-slate-200 bg-white p-5 text-slate-600">{error}</div>;
  }

  if (!pet) {
    return <div className="rounded-lg border border-slate-200 bg-white p-5 text-slate-600">Loading pet details...</div>;
  }

  return (
    <PetDetail
      pet={pet}
      onAddToCart={() =>
        petApi.addToCart(pet.id, 1).then(() => navigate('/cart'))
      }
    />
  );
}
