import * as S from '../../../../modal/CommonModal';

export const EditableFolderItemDropdown = () => {
  return (
    <S.Container
      width="8.5rem"
      height="9.3rem"
      top="0"
      left="1"
      onClick={(e) => e.stopPropagation()}
    >
      <S.DropdownItem height="9.3rem" index="first" itemLength={4}>
        이름 바꾸기
      </S.DropdownItem>
      <S.DropdownItem height="9.3rem" itemLength={4}>
        비우기
      </S.DropdownItem>
      <S.DropdownItem height="9.3rem" itemLength={4}>
        삭제
      </S.DropdownItem>
      <S.DropdownItem height="9.3rem" index="last" itemLength={4}>
        하위 메일함 추가
      </S.DropdownItem>
    </S.Container>
  );
};
