import { useAtom } from 'jotai';

import * as S from './MailFiltering.styles';
import Icon from '../../@common/Icon/Icon';
import AllSelectCheckBox from './AllSelectCheckBox/AllSelectCheckBox';
import MailFilteringDropdown from './MailFilteringDropdown/MailFilteringDropdown';
import { isActiveMailFilteringAtom } from '../../../atoms/mailStatus';

const MailFiltering = () => {
  const [isActiveMailFiltering, setIsActiveMailFiltering] = useAtom(isActiveMailFilteringAtom);

  const handleClickMailFilteringIcon = () => {
    setIsActiveMailFiltering(true);
  };

  return (
    <>
      <S.FilteringViewWrapper>
        <AllSelectCheckBox />
        <Icon
          id="dropdown-button"
          src="/images/icon-arrow-decrease-mono.svg"
          width={13}
          height={13}
          onClick={handleClickMailFilteringIcon}
        />
        {isActiveMailFiltering && <MailFilteringDropdown />}
      </S.FilteringViewWrapper>
    </>
  );
};

export default MailFiltering;
