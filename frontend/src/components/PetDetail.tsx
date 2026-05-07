import { PetDetail as PetDetailType } from '../services/petApi';
import { formatPeso } from '../services/currency';
import PetImage from './PetImage';
import { Grid, Paper, Typography, Chip, Button, Box } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

interface PetDetailProps {
  pet: PetDetailType;
  onAddToCart: () => void;
}

export default function PetDetail({ pet, onAddToCart }: PetDetailProps) {
  return (
    <Grid container spacing={3}>
      <Grid item xs={12} lg={8}>
        <Paper elevation={1} sx={{ p: 3 }}>
          <Box
            sx={{
              mb: 3,
              aspectRatio: '4/3',
              width: '100%',
              overflow: 'hidden',
              borderRadius: 1,
              backgroundColor: '#f1f5f9'
            }}
          >
            <PetImage src={pet.imageUrls[0]} alt={pet.name} />
          </Box>
          <Typography variant="h4" component="h1" sx={{ fontWeight: 600, mb: 1 }}>
            {pet.name}
          </Typography>
          <Typography variant="caption" color="textSecondary" sx={{ textTransform: 'uppercase', display: 'block', mb: 3 }}>
            {pet.category}
          </Typography>
          <Typography variant="body1" sx={{ lineHeight: 1.7, color: 'text.secondary' }}>
            {pet.description}
          </Typography>
        </Paper>
      </Grid>
      <Grid item xs={12} lg={4}>
        <Paper elevation={1} sx={{ p: 3, height: 'fit-content' }}>
          <Typography variant="caption" color="textSecondary" sx={{ textTransform: 'uppercase' }}>
            Adoption fee
          </Typography>
          <Typography variant="h4" sx={{ fontWeight: 600, my: 2 }}>
            {formatPeso(pet.price)}
          </Typography>
          <Chip
            label={pet.availabilityStatus}
            color="success"
            sx={{ mb: 3, backgroundColor: '#f0fdf4', color: '#166534' }}
          />
          <Button
            onClick={onAddToCart}
            variant="contained"
            color="success"
            size="large"
            fullWidth
            startIcon={<ShoppingCartIcon />}
            sx={{ py: 1.5, fontWeight: 600 }}
          >
            Add to cart
          </Button>
        </Paper>
      </Grid>
    </Grid>
  );
}
