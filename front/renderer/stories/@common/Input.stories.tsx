import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Input } from '../../components/@common/Input';
import theme from '../../styles/theme';

export default {
  title: '@common/Input',
  component: Input,
} as ComponentMeta<typeof Input>;

const Template: ComponentStory<typeof Input> = (args) => <Input {...args} />;

export const DefaultInput: ComponentStory<typeof Input> = Template.bind({});
DefaultInput.args = {
  type: 'text',
  mainColor: theme.colors.BLUE_002,
  defaultColor: theme.colors.GRAY_008,
  width: 350,
  height: 55,
  borderRadius: 5,
  fontSize: 18,
  paddingLeft: 10,
  label: '로그인',
  labelGap: 11,
};
