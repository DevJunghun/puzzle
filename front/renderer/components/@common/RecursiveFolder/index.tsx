// data가 [aa,bb,cc,dd] 오면 depth1이 aa인 방식

import React, { useState } from 'react';
import styled from 'styled-components';
import { RecursiveFolderItem } from './RecursiveFolderItem';

interface RecursiveFolderProps {
  mainIconUrl: string;
  mainText: string;
  children?: React.ReactNode;
  data: string[];
}

export const RecursiveFolder = ({
  mainIconUrl,
  mainText,
  children,
  data,
}: RecursiveFolderProps) => {
  const initialState = [...new Array(data.length).fill(0).map((_, idx) => idx)].reduce(
    (acc, _, index) => {
      return { ...acc, [index]: true };
    },
    {},
  );
  const [isOpenObject, setIsOpenObject] = useState(initialState);

  const handleClickRecursiveFolderItem = (idx: number) => {
    setIsOpenObject((prev) => ({ ...prev, [idx]: !prev[idx] }));
  };

  const getFirstNotOpenedValue = () => {
    let result = Infinity;
    const arr = Object.entries(isOpenObject);

    for (let i = 0; i < arr.length; i++) {
      if (!arr[i][1]) {
        result = Number(arr[i][0]);
        return result;
      }
    }

    return result;
  };

  return (
    <>
      <MainFolderContainer>
        <img src={mainIconUrl} width={24} height={24} />
        <div>{mainText}</div>
      </MainFolderContainer>
      {children ? children : null}
      {data.map((text, idx) => (
        <RecursiveFolderItem
          key={idx}
          isFolded={idx !== getFirstNotOpenedValue()}
          isOpened={idx <= getFirstNotOpenedValue()}
          depth={idx}
          folderName={text}
          onClick={handleClickRecursiveFolderItem}
        />
      ))}
    </>
  );
};

const MainFolderContainer = styled.div`
  display: flex;
  align-items: center;
  font-weight: bold;
  font-size: 16px;
  gap: 11px;
  padding: 13px 0;
`;
