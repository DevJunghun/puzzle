import { ComponentStory, ComponentMeta } from '@storybook/react';
import MailHeader from '../../components/mailBox/MailHeader/MailHeader';

export default {
  title: 'Components/MailHeader',
  component: MailHeader,
} as ComponentMeta<typeof MailHeader>;

const Template: ComponentStory<typeof MailHeader> = (args) => <MailHeader {...args} />;

export const Default = Template.bind({});
Default.args = {};
