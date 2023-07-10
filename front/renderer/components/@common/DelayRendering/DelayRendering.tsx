import { useEffect, useState } from 'react';

const DelayRendering = ({ children }: { children: React.ReactNode }) => {
  const [isReadyToRender, setIsReadyToRender] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setIsReadyToRender(true);
    }, 100);
  });

  return <>{isReadyToRender ? children : null}</>;
};

export default DelayRendering;
