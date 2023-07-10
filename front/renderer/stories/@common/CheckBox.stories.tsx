import { ComponentStory, ComponentMeta } from '@storybook/react';
import CheckBox from '../../components/@common/CheckBox/CheckBox';

export default {
  title: '@common/CheckBox',
  component: CheckBox,
} as ComponentMeta<typeof CheckBox>;

const Template: ComponentStory<typeof CheckBox> = (args) => <CheckBox {...args} />;

export const NotCheckedBox: ComponentStory<typeof CheckBox> = Template.bind({});
NotCheckedBox.args = {
  isChecked: false,
};

export const CheckedBox: ComponentStory<typeof CheckBox> = Template.bind({});
CheckedBox.args = {
  isChecked: true,
};
