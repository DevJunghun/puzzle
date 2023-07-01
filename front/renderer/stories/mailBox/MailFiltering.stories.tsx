import { ComponentStory, ComponentMeta } from '@storybook/react';
import MailFiltering from '../../components/mailBox/MailFiltering/MailFiltering';

export default {
  title: 'Components/MailFiltering',
  component: MailFiltering,
  parameters: {
    backgrounds: {
      default: 'gray',
    },
  },
} as ComponentMeta<typeof MailFiltering>;

const Template: ComponentStory<typeof MailFiltering> = () => <MailFiltering />;

export const Default: ComponentStory<typeof MailFiltering> = Template.bind({});
Default.args = {};
