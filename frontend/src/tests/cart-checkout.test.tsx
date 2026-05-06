import { render, screen } from '@testing-library/react';
import CartPanel from '../components/CartPanel';

const cart = {
  cartId: '1',
  items: [],
  subtotal: 0
};

test('renders empty cart', () => {
  render(<CartPanel cart={cart} onCheckout={() => undefined} />);
  expect(screen.getByText('Cart is empty')).toBeInTheDocument();
});
