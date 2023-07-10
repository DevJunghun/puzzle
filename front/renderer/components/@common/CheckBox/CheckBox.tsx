import Icon from '../Icon/Icon';

interface CheckBoxProps {
  isChecked: boolean;
}

const CheckBox = ({ isChecked }: CheckBoxProps) => {
  const svgUrl = isChecked ? '/images/icon-check-mono.svg' : '/images/icon-no-check-mono.svg';

  return <Icon src={svgUrl} width={20} height={20} />;
};

export default CheckBox;
