import { Box, Button } from '@mui/material';
import { Category } from '../services/petApi';

interface CategoryListProps {
  categories: Category[];
  selectedId?: string | null;
  onSelect: (category: Category) => void;
}

export default function CategoryList({ categories, selectedId, onSelect }: CategoryListProps) {
  return (
    <Box sx={{ display: 'flex', gap: 1, flexWrap: 'wrap', mb: 2 }}>
      {categories.map((category) => (
        <Button
          key={category.id}
          onClick={() => onSelect(category)}
          variant={selectedId === category.id ? 'contained' : 'outlined'}
          color={selectedId === category.id ? 'success' : 'inherit'}
          sx={{ whiteSpace: 'nowrap' }}
        >
          {category.displayLabel}
        </Button>
      ))}
    </Box>
  );
}
