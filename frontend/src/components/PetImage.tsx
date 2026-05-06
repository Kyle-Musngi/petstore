import { useState } from 'react';

interface PetImageProps {
  src?: string | null;
  alt: string;
}

export default function PetImage({ src, alt }: PetImageProps) {
  const [failed, setFailed] = useState(false);

  if (!src || failed) {
    return (
      <div className="flex h-full w-full items-center justify-center bg-[linear-gradient(135deg,#ecfdf5,#e0f2fe)] p-4">
        <div className="flex h-16 w-16 items-center justify-center rounded-full bg-white text-2xl font-semibold text-emerald-800 shadow-sm">
          {alt.slice(0, 1).toUpperCase()}
        </div>
      </div>
    );
  }
  return <img src={src} alt={alt} onError={() => setFailed(true)} className="h-full w-full object-cover" />;
}
