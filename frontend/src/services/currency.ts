const pesoFormatter = new Intl.NumberFormat('en-PH', {
  style: 'currency',
  currency: 'PHP'
});

export function formatPeso(value: number) {
  return pesoFormatter.format(value);
}
