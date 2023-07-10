import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Button } from '../../components/@common/Button';

export default {
  title: '@common/Button',
  component: Button,
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = (args) => <Button {...args} />;

export const DefaultButton: ComponentStory<typeof Button> = Template.bind({});
DefaultButton.args = {
  borderRadius: 5,
  width: '86',
  height: '41',
  children: '로그인',
};

export const PrimaryButton: ComponentStory<typeof Button> = Template.bind({});
PrimaryButton.args = {
  isPrimary: true,
  borderRadius: 5,
  width: '86',
  height: '41',
  children: '로그인',
};
