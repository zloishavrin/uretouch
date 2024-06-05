export const SVGSelector = ({ type, className }) => {

    switch (type) {
        case 'logo':
            return (
                <svg className={className} viewBox="0 0 170 170" fill="none">
                    <defs>
                        <clipPath id="clip1_27">
                            <rect id="Frame 2 1" width="170.000000" height="170.000000" fill="white" fillOpacity="0"/>
                        </clipPath>
                    </defs>
                    <rect id="Frame 2 1" width="170.000000" height="170.000000" fill="#FFFFFF" fillOpacity="0"/>
                    <g clipPath="url(#clip1_27)">
                        <path id="Vector" d="M86 158.06C45.09 158.06 11.93 124.9 11.93 84C11.93 43.09 45.09 9.93 86 9.93C126.9 9.93 160.06 43.09 160.06 84C160.06 124.9 126.9 158.06 86 158.06Z" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                        <path id="Vector" d="M45.71 38.97L45.71 110.3L61.5 123.81C65.44 127.18 70.45 129.03 75.62 129.03L99.62 129.03" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                        <path id="Vector" d="M126.29 129.03L126.29 57.69L110.49 44.18C106.55 40.81 101.55 38.97 96.37 38.97L72.37 38.97" stroke="#FFC42C" strokeOpacity="1.000000" strokeWidth="11.000000"/>
                    </g>
                </svg>
            );
        case 'back':
            return (
                <svg viewBox="0 0 240.823 240.823" className={className}>
                    <g>
                        <path
                            id="Chevron_Right"
                            d="M57.633,129.007L165.93,237.268c4.752,4.74,12.451,4.74,17.215,0c4.752-4.74,4.752-12.439,0-17.179 l-99.707-99.671l99.695-99.671c4.752-4.74,4.752-12.439,0-17.191c-4.752-4.74-12.463-4.74-17.215,0L57.621,111.816 C52.942,116.507,52.942,124.327,57.633,129.007z"
                            fill="#ffffff"
                        ></path>
                    </g>
                </svg>
            )
        case 'arrow-down' :
            return (
                <svg width="26.643799" height="23.007812" viewBox="0 0 26.6438 23.0078" className={className}>
	                <defs/>
	                <path id="Arrow 2" d="M15.32 16.17L23.22 8.27C24.01 7.48 25.25 7.48 26.04 8.27C26.84 9.06 26.84 10.3 26.04 11.1L14.73 22.41C13.94 23.2 12.69 23.2 11.9 22.41L0.59 11.1C-0.2 10.3 -0.2 9.06 0.59 8.27C1.38 7.48 2.63 7.48 3.42 8.27L11.32 16.17L11.32 0L15.32 0L15.32 16.17Z" fill="#FFFFFF" fillOpacity="1.000000" fillRule="evenodd"/>
                </svg>
            )
        default:
            return ''
    }

}