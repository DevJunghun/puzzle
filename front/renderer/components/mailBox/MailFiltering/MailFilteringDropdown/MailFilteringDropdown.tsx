import { useRef } from 'react';
import { useSetAtom } from 'jotai';
import * as S from '../../../modal/CommonModal';
import useOutsideClick from '../../../../hooks/@common/useOutsideClick';
import { isActiveMailFilteringAtom } from '../../../../atoms/mailStatus';

const MailFilteringDropdown = () => {
  const dropdownRef = useRef(null);
  const setIsActiveMailFiltering = useSetAtom(isActiveMailFilteringAtom);
  useOutsideClick(dropdownRef, () => {
    setIsActiveMailFiltering(false);
  });

  return (
    <S.Container ref={dropdownRef} width="8rem" height="13rem" top="28">
      <S.DropdownItem index="first" height="13rem" itemLength={6}>
        전체 선택
      </S.DropdownItem>
      <S.DropdownItem height="13rem" itemLength={6}>
        선택 안함
      </S.DropdownItem>
      <S.DropdownItem height="13rem" itemLength={6}>
        읽음
      </S.DropdownItem>
      <S.DropdownItem height="13rem" itemLength={6}>
        읽지 않음
      </S.DropdownItem>
      <S.DropdownItem height="13rem" itemLength={6}>
        별표
      </S.DropdownItem>
      <S.DropdownItem index="last" height="13rem" itemLength={6}>
        별표 없음
      </S.DropdownItem>
    </S.Container>
  );
};

export default MailFilteringDropdown;
