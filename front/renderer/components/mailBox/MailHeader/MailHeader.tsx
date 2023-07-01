import * as S from './MailHeader.styles';
import { useAtom } from 'jotai';
import { mailBoxCategoryStatusAtom } from '../../../atoms/mailStatus';
import MailBoxCategory from './MailBoxCategory/MailBoxCategory';
import Icon from '../../@common/Icon/Icon';
import { globalSideBarAtom } from '../../../atoms/global';
import DelayRendering from '../../@common/DelayRendering/DelayRendering';

const info = [
  { text: '받은 편지함', count: 233, type: 'send' },
  { text: '보낸 편지함', count: 122, type: 'receive' },
  { text: '중요 편지함', count: 8, type: 'important' },
  { text: '휴지통', count: 12, type: 'trash' },
] as const;

const MailHeader = ({}) => {
  const [mailBoxCategoryStatus, setMailBoxCategoryStatus] = useAtom(mailBoxCategoryStatusAtom);
  const [isSideBarOpen, setSideBarOpen] = useAtom(globalSideBarAtom);

  const handleClickMailBoxCategory = (category: typeof mailBoxCategoryStatus) => {
    setMailBoxCategoryStatus(category);
  };

  const handleClickSideBar = () => {
    setSideBarOpen((prev) => !prev);
  };

  return (
    <S.Container>
      <Icon
        src="/images/icon-line-three-mono.svg"
        width={24}
        height={24}
        style={{ marginRight: '0.5rem' }}
      />
      {info.map(({ text, count, type }) => (
        <MailBoxCategory
          key={type}
          text={text}
          count={count}
          isActive={mailBoxCategoryStatus === type}
          onClick={() => {
            handleClickMailBoxCategory(type);
          }}
        />
      ))}
      <S.EtcIconsWrapper>
        <Icon src="/images/notifications.svg" width={24} height={24} />
        <Icon src="/images/icon-pencil-mono.svg" width={24} height={24} />
        <Icon src="/images/icon-search-mono.svg" width={24} height={24} />
        {!isSideBarOpen && (
          <DelayRendering>
            <Icon
              src="/images/icon-expand-sidebar.svg"
              width={34}
              height={34}
              onClick={handleClickSideBar}
            />
          </DelayRendering>
        )}
      </S.EtcIconsWrapper>
    </S.Container>
  );
};

export default MailHeader;
