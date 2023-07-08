import { useState } from 'react';
import styled, { css } from 'styled-components';
import { EditableFolderItemDropdown } from './EditableFolderItemDropdown';

interface RecursiveFolderItemProps {
  isOpened: boolean;
  depth: number;
  folderName: string;
  onClick: (idx: number) => void;
  isFolded: boolean;
}
// 하위 폴더추가 기능

// json data정보에 text, depth, 부모요소를 넣어줘야힘.
// depth에 따라서 그려줘야함.
// 같은 depth에서는 가나다순으로 정렬진행
// 우클릭하여 로직 처리하자
// 먼들기 시작할떄 폴더모양아이콘에 옆에 input으로 처리 -> post로 처리하면서 optimistic update를 이용하여 바로 get처리 진행 -> 정보 update
// 어떻게 depth를 알 수 있을끼? 만약 depth0에서 하위 폴더 만들기를 했다면? -> handleClickFolder...에서 현재의 depth를 받고 그거의 +1한거를 depth로 처리하자

export const RecursiveFolderItem = ({
  onClick,
  depth,
  folderName,
  isFolded,
  isOpened,
}: RecursiveFolderItemProps) => {
  const [isEditable, setIsEditable] = useState(false);
  const [isOpenDropdown, setIsOpenDropdown] = useState(false);

  if (!isOpened) {
    return null;
  }

  return (
    <Container
      depth={depth}
      onClick={() => onClick(depth)}
      onMouseOver={() => {
        setIsEditable(true);
      }}
      onMouseLeave={() => {
        setIsEditable(false);
        setIsOpenDropdown(false);
      }}
    >
      <img
        src={
          isFolded
            ? '/images/icon-arrow-decrease-mono.svg'
            : '/images/icon-arrow-right-small-mono.svg'
        }
        width={18}
        height={18}
        style={{ marginRight: '15.5px' }}
      />
      <img src="/images/icon-folder-mono.svg" style={{ marginRight: '15.5px' }} />
      <div>{folderName}</div>
      <EditableContainer>
        {isEditable ? (
          <PencilIcon
            src="/images/icon-pencil-mono.svg"
            width={18}
            height={18}
            onMouseOver={(e) => {
              e.stopPropagation();
              setIsOpenDropdown(true);
            }}
            onMouseLeave={(e) => {
              e.stopPropagation();
            }}
          />
        ) : null}
        {isOpenDropdown ? <EditableFolderItemDropdown /> : null}
      </EditableContainer>
    </Container>
  );
};

const Container = styled.div<{ depth: number }>`
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.colors.GRAY_008};
  font-size: 15px;
  padding: 5px 0;
  ${({ depth }) => `margin-left: calc(12px * ${depth + 1});`}
  user-select: none;
  cursor: pointer;

  :hover {
    color: ${({ theme }) => theme.colors.BLUE_001};
  }
`;

const PencilIcon = styled.img`
  margin-left: auto;
  cursor: pointer;
`;

const EditableContainer = styled.div`
  margin-left: auto;
  position: relative;
`;
