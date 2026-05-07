import { Link } from 'react-router-dom';
import { Grid, Card, CardMedia, CardContent, Typography, Chip, Box } from '@mui/material';
import { PetSummary } from '../services/petApi';
import { formatPeso } from '../services/currency';
import PetImage from './PetImage';

interface PetListProps {
  pets: PetSummary[];
}

export default function PetList({ pets }: PetListProps) {
  return (
    <Grid container spacing={2}>
      {pets.map((pet) => (
        <Grid item xs={12} sm={6} md={4} key={pet.id}>
          <Link to={`/pets/${pet.id}`} style={{ textDecoration: 'none' }}>
            <Card
              sx={{
                height: '100%',
                display: 'flex',
                flexDirection: 'column',
                transition: 'all 0.3s ease',
                '&:hover': {
                  boxShadow: 4,
                  transform: 'translateY(-4px)',
                  borderColor: 'success.main'
                }
              }}
            >
              <CardMedia
                sx={{
                  height: 200,
                  backgroundColor: '#f1f5f9',
                  position: 'relative',
                  overflow: 'hidden'
                }}
              >
                <PetImage id={pet.id} src={pet.imageUrl} alt={pet.name} />
              </CardMedia>
              <CardContent sx={{ flexGrow: 1 }}>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', mb: 1 }}>
                  <Box>
                    <Typography variant="h6" component="div" sx={{ fontWeight: 600 }}>
                      {pet.name}
                    </Typography>
                    <Typography variant="caption" color="textSecondary" sx={{ textTransform: 'uppercase' }}>
                      {pet.category}
                    </Typography>
                  </Box>
                  <Typography variant="body2" sx={{ fontWeight: 600 }}>
                    {formatPeso(pet.price)}
                  </Typography>
                </Box>
                <Chip
                  label={pet.availabilityStatus}
                  color="success"
                  variant="filled"
                  size="small"
                  sx={{ mt: 1, backgroundColor: '#f0fdf4', color: '#166534' }}
                />
              </CardContent>
            </Card>
          </Link>
        </Grid>
      ))}
    </Grid>
  );
}
