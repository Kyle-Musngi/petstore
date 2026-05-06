import { render, screen } from '@testing-library/react';
import CategoryList from '../components/CategoryList';

const categories = [
  { id: '1', name: 'dogs', displayLabel: 'Dogs', isActive: true }
];

test('renders category list', () => {
  render(<CategoryList categories={categories} onSelect={() => undefined} />);
  expect(screen.getByText('Dogs')).toBeInTheDocument();
});
