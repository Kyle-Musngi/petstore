import { useEffect, useRef, useState } from 'react';


interface PetImageProps {
  id?: string;
  src?: string | string[] | null;
  alt: string;
}

export default function PetImage({ id, src, alt }: PetImageProps) {
  const [urls, setUrls] = useState<string[]>(ReactUrlList(src));
  const [index, setIndex] = useState(0);
  const triedDetail = useRef(false);

  useEffect(() => {
    setUrls(ReactUrlList(src));
    setIndex(0);
    triedDetail.current = false;
  }, [src]);

  const current = urls[index];

  async function fetchDetailImages() {
    if (!id) return;
    try {
      const res = await fetch(`/musngi/api/v1/pets/${id}`);
      if (!res.ok) return;
      const body = await res.json();
      const imageUrls = body.data?.imageUrls ?? [];
      if (Array.isArray(imageUrls) && imageUrls.length > 0) {
        setUrls(imageUrls.filter(Boolean));
        setIndex(0);
      }
    } catch (e) {
      // ignore
    }
  }

  function handleError() {
    // try next url, otherwise try fetching detail images once, then show placeholder
    if (index < urls.length - 1) {
      setIndex(i => i + 1);
      return;
    }
    if (!triedDetail.current && id) {
      triedDetail.current = true;
      void fetchDetailImages();
      return;
    }
    setIndex(-1);
  }

  if (!current || index === -1) {
    return (
      <div className="flex h-full w-full items-center justify-center bg-[linear-gradient(135deg,#ecfdf5,#e0f2fe)] p-4">
        <div className="flex h-16 w-16 items-center justify-center rounded-full bg-white text-2xl font-semibold text-emerald-800 shadow-sm">
          {alt.slice(0, 1).toUpperCase()}
        </div>
      </div>
    );
  }

  return (
    // use crossOrigin to improve cache/CORS handling; onError tries next url
    // eslint-disable-next-line jsx-a11y/img-redundant-alt
    <img
      src={current}
      alt={alt}
      onError={handleError}
      crossOrigin="anonymous"
      className="h-full w-full object-cover"
    />
  );
}

function ReactUrlList(src?: string | string[] | null): string[] {
  if (!src) return [];
  if (Array.isArray(src)) return src.filter(Boolean) as string[];
  return [src];
}
