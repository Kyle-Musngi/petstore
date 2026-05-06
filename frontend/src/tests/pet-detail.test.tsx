import { render, screen } from '@testing-library/react';
import PetDetail from '../components/PetDetail';

const pet = {
  id: '1',
  name: 'Bella',
  category: 'dogs',
  description: 'Friendly',
  availabilityStatus: 'available',
  imageUrls: [],
  price: 250
};

test('renders pet detail name', () => {
  render(<PetDetail pet={pet} onAddToCart={() => undefined} />);
  expect(screen.getByText('Bella')).toBeInTheDocument();
});
