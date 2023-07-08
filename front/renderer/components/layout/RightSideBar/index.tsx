import { useAtom } from 'jotai';
import { globalSideBarAtom } from '../../../atoms/global';
import DelayRendering from '../../@common/DelayRendering/DelayRendering';
import Icon from '../../@common/Icon/Icon';
import * as S from './index.styles';

const SideBar = () => {
  const [isSideBarOpen, setIsSideBarOpen] = useAtom(globalSideBarAtom);

  const handleClickSideBarContractIcon = () => {
    setIsSideBarOpen(false);
  };

  return (
    <S.Container isOpen={isSideBarOpen}>
      {isSideBarOpen && (
        <DelayRendering>
          <S.ContractSideBarIcon onClick={handleClickSideBarContractIcon}>
            <Icon src="/images/icon-sidebar-contract.svg" width={34} height={34} />
          </S.ContractSideBarIcon>
        </DelayRendering>
      )}
    </S.Container>
  );
};

export default SideBar;
