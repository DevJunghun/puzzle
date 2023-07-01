import { ComponentStory, ComponentMeta } from '@storybook/react';
import MailBoxCategory from '../../components/mailBox/MailHeader/MailBoxCategory/MailBoxCategory';

export default {
  title: 'Components/MailHeader/MailBoxCategory',
  component: MailBoxCategory,
} as ComponentMeta<typeof MailBoxCategory>;

const Template: ComponentStory<typeof MailBoxCategory> = (args) => <MailBoxCategory {...args} />;

export const NotActiveCategory: ComponentStory<typeof MailBoxCategory> = Template.bind({});
NotActiveCategory.args = {
  text: '받은 편지함',
  count: 233,
  isActive: false,
};

export const ActiveCategory: ComponentStory<typeof MailBoxCategory> = Template.bind({});
ActiveCategory.args = {
  text: '보낸 편지함',
  count: 122,
  isActive: true,
};
