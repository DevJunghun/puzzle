import { useEffect, RefObject } from 'react';

const useOutsideClick = <T extends HTMLElement>(ref: RefObject<T>, callback: () => void) => {
  const handleClick = (e: MouseEvent) => {
    if ((e.target as HTMLElement).id === 'dropdown-button') {
      return;
    }

    if (ref.current && !ref.current.contains(e.target as Node)) {
      callback();
    }
  };

  useEffect(() => {
    document.addEventListener('click', handleClick);

    return () => {
      document.removeEventListener('click', handleClick);
    };
  }, []);
};

export default useOutsideClick;
