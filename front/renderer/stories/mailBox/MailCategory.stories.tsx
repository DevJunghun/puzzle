import { ComponentStory, ComponentMeta } from '@storybook/react';
import MailCategory from '../../components/mailBox/MailCategory/MailCategory';

export default {
  title: 'components/MailCategory',
  component: MailCategory,
} as ComponentMeta<typeof MailCategory>;

const Template: ComponentStory<typeof MailCategory> = (args) => <MailCategory {...args} />;

export const Default: ComponentStory<typeof MailCategory> = Template.bind({});
Default.args = {
  text: '공지',
};
