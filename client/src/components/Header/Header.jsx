import React from 'react'
import { useLocation } from 'react-router-dom'
import { HeaderButton } from '../HeaderButton/HeaderButton';
import styles from './Header.module.css';

export const Header = () => {

  const location = useLocation();
  const currentPath = location.pathname;

  return (
    <header className={styles.header}>
        <div className={styles.headerContainer}>
          <HeaderButton
            currentPath ={currentPath}
            path={'/generation'}
            title={'Генерация'}
          />
          <HeaderButton
            currentPath ={currentPath}
            path={'/history'}
            title={'История'}
          />
          <HeaderButton
            currentPath ={currentPath}
            path={'/user'}
            title={'Аккаунт'}
          />
      </div>
    </header>
  )
}
