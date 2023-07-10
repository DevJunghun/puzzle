import styled from 'styled-components';
import { Button } from '../components/@common/Button';
import { Input } from '../components/@common/Input';
import theme from '../styles/theme';
import CheckBox from '../components/@common/CheckBox/CheckBox';
import { useRouter } from 'next/router';

const defaultInputStyle = {
  width: 350,
  height: 55,
  mainColor: theme.colors.BLUE_002,
  defaultColor: theme.colors.GRAY_008,
  borderRadius: 5,
  fontSize: 18,
  paddingLeft: 10,
  labelGap: 11,
};

const defaultButtonStyle = {
  borderRadius: 5,
  width: '86',
  height: '41',
};

const SignIn = () => {
  const router = useRouter();

  return (
    <Layout>
      <SigninWrapper>
        <Input {...defaultInputStyle} type="text" label="아이디" style={{ marginBottom: 30 }} />
        <Input
          {...defaultInputStyle}
          type="password"
          label="비밀번호"
          style={{ marginBottom: 33 }}
        />
        <BottomLayerWrapper>
          <AutoLoginCheckBox>
            <CheckBox isChecked={true} />
            <AutoLoginText>자동 로그인</AutoLoginText>
          </AutoLoginCheckBox>
          <ButtonWrapper>
            <Button
              {...defaultButtonStyle}
              isPrimary={false}
              onClick={() => {
                router.push('/signup');
              }}
            >
              회원가입
            </Button>
            <Button {...defaultButtonStyle} isPrimary={true}>
              로그인
            </Button>
          </ButtonWrapper>
        </BottomLayerWrapper>
      </SigninWrapper>
    </Layout>
  );
};

const Layout = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
`;

const SigninWrapper = styled.div``;

const BottomLayerWrapper = styled.div`
  display: flex;
  align-items: center;
`;

const ButtonWrapper = styled.div`
  margin-left: auto;
`;

const AutoLoginCheckBox = styled.div`
  display: flex;
  color: ${({ theme }) => theme.colors.GRAY_008};
  align-items: center;
  gap: 8px;
`;

const AutoLoginText = styled.span`
  height: 20px;
  line-height: 22px;
`;

export default SignIn;
